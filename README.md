# Sistema para gestão de clínica médica

O sistema deve dar suporte à gestão de clínica onde trabalha uma equipe de médicos. Será usado pelos recepcionistas para marcação de consultas e também pelos médicos, para registro do histórico dos pacientes. Deve ser controlada a recepção de resultados de exames que sejam enviados diretamente dos laboratórios para os médicos; por opção do médico, estes resultados podem ser entregues aos pacientes, ou guardados na clínica para consulta posterior. O médico pode também autorizar que os exames sejam disponibilizados online ao respectivo paciente; neste caso, o paciente usará uma chave única para visualizar o resultado de seus exames.

## Instruções para o Trabalho Prático (TP)

Cada grupo deve desenvolver um projeto de software sobre o tema alocado contendo:

<ol>
<li>Um modelo de processo, incluindo: as atividades para desenvolvimento do projeto,
responsáveis pelas atividades e um cronograma para execução das atividades.</li>
<li>Um documento de especificação de requisitos, incluindo: Diagramas de Casos de
Uso e descrição dos cenários de casos de uso.</li>
<li>Um documento de projeto arquitetural, incluindo um Diagrama de Componentes.</li>
<li>Um documento de projeto detalhado, incluindo: Diagramas de Classes, Diagramas
de Sequência e outros dois tipos de diagramas definidos na UML.</li>
<li>Implementação parcial do sistema em Java</li>
<li>Testes automatizados JUnit para a implementação parcial.</li>
</ol>

## Grupo

<table>
  <tr>
    <td align="center"><a href="https://github.com/MerciaCaroline"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/26381378?v=4" width="100px;" alt=""/><br /><sub><b>Mércia Martins</b></sub></a><br />
    <td align="center"><a href="https://github.com/marconefaria"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/62022318?v=4" width="100px;" alt=""/><br /><sub><b>Marcone Faria</b></sub></a><br />
    <td align="center"><a href="https://github.com/enriqca"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/56132430?v=4" width="100px;" alt=""/><br /><sub><b>Henrique </b></sub></a><br />
  </tr>
</table>

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
