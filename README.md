Data Pipeline ANS - Processamento Contábil
Este repositório contém uma solução de ETL (Extração, Transformação e Carga) desenvolvida em Java para consolidar dados contábeis de operadoras de saúde suplementar, conforme requisitos da ANS.

🛠️ Decisões Técnicas e Arquitetura
O projeto foi estruturado com foco em resiliência e boas práticas de desenvolvimento:

Pipeline de Dados (Java)
Resiliência no Download: Devido à instabilidade frequente nos servidores da ANS, o sistema implementa um User-Agent para evitar bloqueios e possui uma rotina de contingência que utiliza dados locais na pasta extraido caso o download falhe.

Tratamento de Dados: Seguindo uma análise crítica, valores negativos de despesas foram normalizados para 0.0, garantindo que inconsistências contábeis não distorçam os resultados finais.

Validação: Implementação de coluna de status para validação de integridade de CNPJ no arquivo consolidado.

Camada de Dados (SQL)
Foi adotado um modelo relacional normalizado para garantir a integridade referencial e facilitar consultas analíticas:

Tabela: operadoras | Coluna | Tipo | Descrição | | :--- | :--- | :--- | | cnpj | VARCHAR(14) | Chave Primária | | razao_social | VARCHAR(255) | Nome da operadora | | uf | CHAR(2) | Estado |

Tabela: despesas | Coluna | Tipo | Descrição | | :--- | :--- | :--- | | id | SERIAL | Chave Primária | | cnpj_operadora | VARCHAR(14) | Chave Estrangeira (FK) | | valor_despesa | DECIMAL(15,2) | Valor processado |

🚀 Tecnologias e Versionamento
Java 21 & Maven para gestão de dependências.

Git para controle de versão, seguindo um fluxo de commits organizado.

SQL ANSI para portabilidade entre diferentes bancos de dados.

📂 Estrutura de Pastas
/src/main/java: Lógica de processamento e serviços.

/src/main/resources/scripts_sql: Scripts DDL e Queries analíticas.

/arquivos_ans: Local de saída do arquivo consolidado.csv.

⚙️ Como Executar
Importe o projeto como um projeto Maven.

Execute a classe com.intuitive.main.MainApp.

O log no console indicará se o processamento utilizou o download em tempo real ou o mock local.