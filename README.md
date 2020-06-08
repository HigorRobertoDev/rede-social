# Rede Social

[![FVCproductions](https://raw.githubusercontent.com/HigorRobertoDev/imagens/master/rede-social/grafico-rede-social.png)]()


## Sobre

O serviço de rede social faz um relacionamento entre usuários e também lista os amigos dos amigos como na imagem acima.


## Principais Funcionalidades

- Criar usuário
- Faz o relacionamento entre usuário
- Lista todos os usuários
- Lista os amigos de um determinado usuário
- Lista os amigos dos amigos de um determinado usuário


### Bibliotecas utilizadas para fazer a persistência 

- hsqldb (Banco de dados na própria aplicação)


### Serviços REST da aplicação

```bash
POST: localhost:8080/rede-social/usuario/salvar
POST: localhost:8080/rede-social/usuario/vincular/amigo
POST: localhost:8080/rede-social/usuario/buscar/amigo
POST: localhost:8080/rede-social/usuario/buscar/amigos/amigo
POST: localhost:8080/rede-social/usuarios
```


## Uso

Foi implementado um gráfico utilizando [amcharts](https://www.amcharts.com/demos/force-directed-tree/), o gráfico está mostra o relacionamento entre amigos de amigos e está sendo demonstrado no arquivo index.html. O arquivo faz o consumo do serviço localhost:8080/rede-social/usuario/buscar/amigos/amigo.


Para mais detalhes do uso dos serviços da rede social, importe o arquivo:
rede-social.postman_collection.json no Postman.
