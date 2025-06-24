package io.github.pedrozaz.axiom.adapter.in.web;

import io.github.pedrozaz.axiom.adapter.in.web.dto.SubmitContentRequest;
import io.github.pedrozaz.axiom.adapter.in.web.dto.SubmitContentResponse;
import io.github.pedrozaz.axiom.application.port.in.SubmitContentUseCase;
import io.github.pedrozaz.axiom.domain.model.Content;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/content")
public class ContentController {

    private final SubmitContentUseCase submitContentUseCase;

    public ContentController(SubmitContentUseCase submitContentUseCase) {
        this.submitContentUseCase = submitContentUseCase;
    }

    @PostMapping
    public ResponseEntity<SubmitContentResponse> submit(
            @RequestBody @Valid SubmitContentRequest request) {
        var contentToSubmit = Content.builder()
                .textContent(request.textContent())
                .build();

        Content savedContent = submitContentUseCase.submit(contentToSubmit);

        var response = new SubmitContentResponse(savedContent.getId(), savedContent.getStatus().name());

        return ResponseEntity.accepted()
                .location(URI.create("/v1/content/" + savedContent.getId()))
                .body(response);
    }
}
