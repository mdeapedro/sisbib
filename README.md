# sislib

Sistema de Biblioteca

**sislib** é um programa escrito em Java que simula um sistema de biblioteca
universitário.

Produzido para fins didáticos da disciplina Engenharia de Software 1 da
Universidade Federal da Bahia (UFBA), período 2024.1.

## Introdução

**sislib** gerencia e mantém livros disponiveis em uma biblioteca acadêmica. Ele
permite que três tipos de usuários (estudantes de graduação, estudantes de
pós-graduação e professores) realizem empréstimo, devoluções ou reservem livros.

Adicionalmente, um usuário pode se inscrever para receber notificações sempre
que um livro for reservado por mais de um usuário simultaneamente.

## Uso

### Inicialização do programa

`java Sislib [usuarios] [livros] [exemplares]`

### Solicitar um empréstimo

`emp [codigo_do_usuario] [codigo_do_livro]`

### Devolver um livro

`dev [codigo_do_usuario] [codigo_do_livro]`

### Reservar um livro

`res [codigo_do_usuario] [codigo_do_livro]`

### Observar um livro

`obs [codigo_do_usuario] [codigo_do_livro]`

## Casos de testes

Cada caso de teste deve ser formado por uma linha ou mais de valores separados
por ponto-e-vírgula, seguindo a convenção abaixo:

### Usuários

-   codigo_do_usuario: int
-   tipo_do_usuario: 'Aluno de Graduação' | 'Aluno de Pós-graduação' |
    'Professor'
-   nome_do_usuario: string

```txt
[codigo_do_usuario_1];[tipo_do_usuario_1];[nome_do_usuario_1]
[codigo_do_usuario_2];[tipo_do_usuario_2];[nome_do_usuario_2]
[codigo_do_usuario_3];[tipo_do_usuario_3];[nome_do_usuario_3]
...
```

#### Exemplo

```txt
123;Aluno de Graduação;João da Silva
456;Aluno de Pós-graduação;Luiz Fernando Rodrigues
789;Aluno de Graduação;Pedro Paulo
100;Professor;Carlos Lucena
```

### Livros

-   codigo: int
-   nome: string
-   editora: string
-   autores: string
-   edicao: int
-   ano_publicacao: int

```txt
[codigo_1];[nome_1];[editora_1];[autores_1];[edicao_1];[ano_publicacao_1]
[codigo_2];[nome_2];[editora_2];[autores_2];[edicao_2];[ano_publicacao_2]
[codigo_3];[nome_3];[editora_3];[autores_3];[edicao_3];[ano_publicacao_3]
...
```

#### Exemplo

```txt
100;Engenharia de Software;Addison Wesley;Ian Sommervile;6ª;2000
101;UML – Guia do Usuário;Campus;Grady Booch, James Rumbaugh, Ivar Jacobson;7ª;2000
200;Code Complete;Microsoft Press;Steve McConnell;2ª;2014
201;Agile Software Development, Principles, Patterns, and Practices;Prentice Hall;Robert Martin;1ª;2002
300;Refactoring: Improving the Design of Existing Code;Addison - Wesley Professional;Martin Fowler;1ª;1999
301;Software Metrics: A Rigorous and Practical Approach;CRC Press;Norman Fenton, James Bieman;3ª;2014
400;Design Patterns: Elements of Reusable Object-Oriented Software;Addison - Wesley Professional;Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides;1ª;1994
401;UML Distilled: A Brief Guide to the Standard Object Modeling Language;Addison - Wesley Professional;Martin Fowler;3ª;2003
```

### Exemplares

-   codigo_do_livro: int
-   codigo_do_exemplar: int
-   status_do_exemplar: 'Disponível'

```txt
[codigo_do_livro_1];[codigo_do_exemplar_1];[status_do_exemplar_1]
[codigo_do_livro_2];[codigo_do_exemplar_2];[status_do_exemplar_2]
[codigo_do_livro_3];[codigo_do_exemplar_3];[status_do_exemplar_3]
...
```
