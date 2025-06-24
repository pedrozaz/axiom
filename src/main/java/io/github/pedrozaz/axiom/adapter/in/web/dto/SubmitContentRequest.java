package io.github.pedrozaz.axiom.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record SubmitContentRequest(
        @NotBlank String textContent
) { }
