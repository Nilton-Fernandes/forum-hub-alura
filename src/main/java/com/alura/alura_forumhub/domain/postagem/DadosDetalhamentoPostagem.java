package com.alura.alura_forumhub.domain.postagem;

import java.time.LocalDateTime;

public record DadosDetalhamentoPostagem(Long id, String titulo, String mensagem, LocalDateTime data, Long id_autor, String curso) {
    public DadosDetalhamentoPostagem(Postagem postagem){
        this(
                postagem.getId(),
                postagem.getTitulo(),
                postagem.getMensagem(),
                postagem.getData(),
                postagem.getId(),
                postagem.getCurso());
    }
}
