# Tarefas
## A Fazer

- [ ] Desenhar Diagramas de sequências
- [ ] Desenhar  Diagrama de atividades
- [ ] Desenhar  Diagrama de estado
- [ ] Implementar Paciente MVC
- [X] Implementar Medico MVC
- [X] Implementar Recepcionista MVC
- [ ] Implementar Laboratorio MVC
- [ ] Implementar Admin MVC

# Configurando o ambiente

## Compilando projetos em java no VS Code

Requisitos:
- Java instalado
- VS Code instalado.

Em seguida, o que precisamos fazer é instalar algumas extensões na nossa IDE. São elas:
- Extension Pack for Java;
- Code Runner.

Para facilitar nossa vida, o extension pack já traz um conjunto de extensões necessária para execução do nosso código Java. Com o projeto aberto basta clicar em Run Java

## Configurando JDBC com postgresSQL

1) Faça o download do driver JDBC do PostgreSQL no site oficial: https://jdbc.postgresql.org/
2) Extraia o arquivo baixado e você terá um arquivo JAR (por exemplo, postgresql-x.x.x.jar, onde x.x.x representa a versão).
3) No seu ambiente de desenvolvimento, adicione o arquivo JAR do driver JDBC do PostgreSQL ao classpath do seu projeto. Para configurar no vscode siga os passos:
- Abra java projects no canto inferior esquerdo do VS Code
- Clique no botão + no campo refrenced libraries
- Procure o .jar do seu driver jdbc

Pronto, seu ambiente está configurado
