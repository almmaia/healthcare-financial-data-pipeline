# Healthcare Financial Data Pipeline (Java ETL)

Robust ETL pipeline built in Java to process and consolidate financial data from Brazilian healthcare operators (ANS).

This project simulates a production-style data engineering workflow focused on resilience, validation and relational modeling.

---

## 🚀 Overview

Public healthcare financial data in Brazil is often unstable and inconsistent.
This pipeline demonstrates how to build a resilient backend process capable of extracting, validating and consolidating financial records for analytics and auditing.

The system:

* downloads datasets from ANS
* handles unstable servers with fallback strategy
* normalizes inconsistent financial values
* validates CNPJ integrity
* loads into relational SQL model
* generates consolidated CSV ready for analysis

---

## 🧠 Engineering Decisions

### Resilient Extraction

ANS servers frequently fail or block automated downloads.

Implemented:

* custom User-Agent
* retry strategy
* fallback to local dataset

Ensures pipeline execution even if external source fails.

### Data Normalization

Negative expense values are normalized to `0.0` to prevent financial distortion in aggregated analysis.

### Validation Layer

CNPJ validation status column added to consolidated dataset to ensure data integrity.

---

## 🏗 Architecture

```
Download → Validate → Transform → Normalize → Load → Consolidated Output
```

Clean separation of concerns following backend best practices.

---

## 🗄️ Database Model

### operadoras

| column       | type         | description   |
| ------------ | ------------ | ------------- |
| cnpj         | VARCHAR(14)  | Primary key   |
| razao_social | VARCHAR(255) | Operator name |
| uf           | CHAR(2)      | State         |

### despesas

| column         | type          | description     |
| -------------- | ------------- | --------------- |
| id             | SERIAL        | Primary key     |
| cnpj_operadora | VARCHAR(14)   | Foreign key     |
| valor_despesa  | DECIMAL(15,2) | Processed value |

---

## 🛠 Tech Stack

* Java 21
* Maven
* SQL (ANSI)
* Git
* ETL architecture
* Data validation

---

## 📂 Project Structure

```
src/main/java → processing logic  
src/main/resources/scripts_sql → SQL scripts  
arquivos_ans → consolidated output  
```

---

## ⚙️ Running

1. Import as Maven project
2. Run:

```
MainApp.java
```

Console logs will show if data came from:

* live ANS download
* local fallback dataset

---

## 🎯 What This Project Demonstrates

* Backend engineering with Java
* ETL pipeline design
* Handling unreliable external data
* Financial data normalization
* Relational modeling
* Clean architecture

---

## 👨‍💻 Author

Alan Maia
Backend & Data Engineering focused
Brazil
