package com.tek271.jcraip.ai.gemini.structure;

import java.util.List;

public record Content(String role, List<Part> parts) {
}
