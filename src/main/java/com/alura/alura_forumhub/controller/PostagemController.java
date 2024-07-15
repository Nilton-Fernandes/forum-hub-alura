package com.alura.alura_forumhub.controller;

import com.alura.alura_forumhub.domain.postagem.DadosDetalhamentoPostagem;
import com.alura.alura_forumhub.domain.postagem.DadosPostagem;
import com.alura.alura_forumhub.domain.postagem.DadosPostagemDTO;
import com.alura.alura_forumhub.domain.postagem.PostagemRepository;
import com.alura.alura_forumhub.infra.service.PostagemService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/topicos")
@RestController
@SecurityRequirement(name = "bearer-key")
public class PostagemController {
    @Autowired
    private PostagemService postagemService;

    @Autowired
    private PostagemRepository postagemRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPostagem> criarTopico(@RequestBody @Valid DadosPostagem dados) {
        DadosDetalhamentoPostagem detalhamentoPostagem = postagemService.postar(dados);
        return ResponseEntity.ok(detalhamentoPostagem);
    }

    @GetMapping
    public ResponseEntity<Page<DadosPostagemDTO>> listarPostagem(@PageableDefault(size = 10, sort = "data") Pageable pageable){
        var page = postagemRepository.findAll(pageable)
                .map(DadosPostagemDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<DadosPostagemDTO>> top10Topico(){
        var page = postagemRepository.findTop10ByOrderByDataAsc()
                .stream().map(DadosPostagemDTO::new)
                .toList();

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity topicoEspecifico(@PathVariable Long id){
        var page = postagemService.buscaPorId(id);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizacaoTopico(@RequestBody @Valid DadosPostagem dados){
        var page = postagemService.atualizarTopico(dados);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        postagemService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }


}


