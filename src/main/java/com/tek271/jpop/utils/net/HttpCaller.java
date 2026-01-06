package com.tek271.jpop.utils.net;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

public class HttpCaller {
  public enum Method {
    POST,
    GET
  }

  private String url;
  private Method method = Method.GET;
  private final Headers headers = new Headers();
  private String body;


  public HttpCaller url(String url) {
    this.url = url;
    return this;
  }

  public HttpCaller method(Method method) {
    this.method = method;
    return this;
  }

  public HttpCaller methodPost() {
    return method(Method.POST);
  }

  public HttpCaller methodGet() {
    return method(Method.GET);
  }

  public HttpCaller addHeader(String name, String value) {
    headers.add(name, value);
    return this;
  }

  public HttpCaller contentType(String contentType) {
    headers.contentType(contentType);
    return this;
  }

  public HttpCaller contentTypeJson() {
    headers.contentTypeJson();
    return this;
  }

  public HttpCaller body(String body) {
    this.body = body;
    return this;
  }

  private HttpRequest createRequest() {
    HttpRequest.Builder builder = HttpRequest.newBuilder();
    builder.uri(URI.create(url));

    BodyPublisher bodyPublisher = body == null ? BodyPublishers.noBody() : BodyPublishers.ofString(body);
    builder.method(method.toString(), bodyPublisher);
    headers.toList().forEach(p -> builder.header(p.key(), p.value()));
    return builder.build();
  }

  public HttpResponse<String> send() {
    HttpRequest request = createRequest();
    try (HttpClient client = HttpClient.newHttpClient()) {
      return client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
