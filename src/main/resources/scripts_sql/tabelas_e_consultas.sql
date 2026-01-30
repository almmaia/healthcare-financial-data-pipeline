-- DDL para criação das tabelas (Opção B - Normalizada)
CREATE TABLE operadoras (
                            cnpj VARCHAR(14) PRIMARY KEY,
                            razao_social VARCHAR(255),
                            uf CHAR(2)
);

CREATE TABLE despesas (
                          id SERIAL PRIMARY KEY,
                          cnpj_operadora VARCHAR(14) REFERENCES operadoras(cnpj),
                          valor_despesa DECIMAL(15,2)
);

-- Query analítica: Top 5 operadoras com mais despesas no último ano
SELECT o.razao_social, SUM(d.valor_despesa) as total
FROM despesas d
         JOIN operadoras o ON d.cnpj_operadora = o.cnpj
GROUP BY o.razao_social
ORDER BY total DESC
    LIMIT 5;