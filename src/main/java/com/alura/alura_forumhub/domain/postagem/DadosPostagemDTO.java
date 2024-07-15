package com.alura.alura_forumhub.domain.postagem;

import java.time.LocalDateTime;

public record DadosPostagemDTO(String titulo,
                               String mensagem,
                               Long id,
                               String curso,
                               LocalDateTime data,
                               Boolean ativo) {

    public DadosPostagemDTO(Postagem postagem){
        this(postagem.getTitulo(), postagem.getMensagem(), postagem.getId(), postagem.getCurso(), postagem.getData(),postagem.getAtivo());
    }
}
