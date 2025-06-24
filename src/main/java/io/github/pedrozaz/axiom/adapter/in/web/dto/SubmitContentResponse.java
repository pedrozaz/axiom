package io.github.pedrozaz.axiom.adapter.in.web.dto;

import java.util.UUID;

public record SubmitContentResponse(
        UUID id,
        String status
) { }
