package com.tek271.jcraip;

import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.ai.gemini.GeminiCaller;
import com.tek271.jcraip.instrument.Interceptor;
import com.tek271.jcraip.instrument.InterfaceProxy;
import com.tek271.jcraip.prompt.PromptRunnerImpl;
import com.tek271.jcraip.prompt.PromptRunner;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.List;

import static com.tek271.jcraip.utils.reflect.Creator.createDynaInstance;
import static com.tek271.jcraip.utils.reflect.Creator.subclass;
import static com.tek271.jcraip.utils.reflect.ReflectionTools.*;

public class AiObjectFactory {
  private final PromptRunner promptRunner;

  public AiObjectFactory(PromptRunner promptRunner) {
    this.promptRunner = promptRunner;
  }

  public AiObjectFactory(AiCaller aiCaller) {
    this(new PromptRunnerImpl(aiCaller));
  }

  public AiObjectFactory() {
    this(new PromptRunnerImpl(new GeminiCaller()));
  }

  /**
   * Create a proxy of the given targetClass
   * @param targetClass Can be a class or interface. Note that you cannot proxy static methods in classes.
   * @return a proxy object of the same type as targetClass
   * @param <T> The type of the returned object
   */
  public <T> T createProxy(Class<T> targetClass) {
    if (targetClass == null) {
      throw new NullPointerException("jcraip cannot proxy a null object");
    }
    if (targetClass.isInterface()) {
      return createDynamicProxy(targetClass);
    }
    return createByteBuddyProxy(targetClass);
  }

  /**
   * Create a dynamic proxy for the given targetClass using standard JDK java.lang.reflect.Proxy.
   * The targetClass must be an interface
   * @param targetClass The class for which to create a proxy. Must be an interface.
   * @return a proxy object of the same type as targetClass
   * @param <T> The type of the returned object
   */
  private <T> T createDynamicProxy(Class<T> targetClass) {
    InterfaceProxy interfaceProxy =  new InterfaceProxy(this.promptRunner);
    return interfaceProxy.createProxy(targetClass);
  }

  /**
   * Create a proxy for the given targetClass using ByteBuddy
   * @param targetClass Can be a class, or an abstract class
   * @return a proxy object of the same type as targetClass
   * @param <T> The type of the returned object
   */
  private <T> T createByteBuddyProxy(Class<T> targetClass) {
    DynamicType.Builder<T> subclass = subclass(targetClass);
    List<Method> methods = findListOfPromptMethods(targetClass);

    Interceptor interceptor = new Interceptor(this.promptRunner);
    for (Method method : methods) {
      subclass = subclass
        .method(ElementMatchers.is(method))
        .intercept(MethodDelegation.to(interceptor));
    }

    return createDynaInstance(subclass);
  }

}
