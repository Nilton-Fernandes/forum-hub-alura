package com.alura.alura_forumhub.domain.postagem;

import com.alura.alura_forumhub.domain.user.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Postagem")
@Table(name = "postagens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;
    @CreationTimestamp
    private LocalDateTime data;
    private Boolean ativo = true;
    @Embedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    @NotBlank
    private String curso;


    public Postagem(DadosPostagem dados, Usuario autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = dados.curso();
        this.ativo = true;
        this.autor = autor;
    }

    public void atualizaInfo(DadosPostagem dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
        if (dados.curso() != null){
            this.curso = dados.curso();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
