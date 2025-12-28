Spring Boot Kafka MySQL Ingestion Pipeline
📌 Overview

This project demonstrates a real-world, production-style data ingestion pipeline using:

Spring Boot

Apache Kafka

MySQL (Stored Procedures)

The system ingests data from a public API, publishes it to Kafka, processes it asynchronously using consumers, and persists it into MySQL, while maintaining ordering guarantees.

Architecture:
REST API
   ↓
Controller
   ↓
Service
   ↓
Kafka Producer
   ↓
Kafka Topic (Ordered / Key-based)
   ↓
Kafka Consumer
   ↓
Stored Procedure
   ↓
MySQL

**Key Features:**

Asynchronous processing using Kafka

Decoupled REST ingestion and database writes

Ordering guarantees (single partition / key-based)

Kafka Consumer Groups

MySQL stored procedure integration

Production-ready layering
(Controller → Service → Kafka → DB)

**Tech Stack:**

Java 17

Spring Boot

Spring Kafka

Apache Kafka

MySQL

JDBC (JdbcTemplate)

Maven

**API Endpoint**
Ingest Data:
GET: /new-posts

What it does

Fetches data from a public API

Publishes each record to Kafka

Returns immediately (non-blocking)

Kafka Configuration

Topic: post-topic

Consumer Group: new-post-consumer-group

Ordering

Guaranteed per partition

Can be configured for single-partition strict ordering

Database
Stored Procedure

CREATE PROCEDURE sp_insert_post(
    IN p_id INT,
    IN p_title VARCHAR(255),
    IN p_body TEXT
)
BEGIN
    INSERT INTO posts(id, title, body)
    VALUES (p_id, p_title, p_body);
END;

How It Works (Flow)

REST API receives request

Service fetches external API data

Kafka Producer publishes messages

Kafka stores data durably

Kafka Consumer reads messages asynchronously

Consumer calls stored procedure

Data is inserted into MySQL

✅ Ordering Guarantee

Kafka guarantees ordering within a partition.

Single partition → strict ordering

Key-based partitioning → per-entity ordering

📈 Benefits

High throughput

No REST API blocking

Scalable consumer processing

Fault tolerance via Kafka offsets

Easy horizontal scaling

🧪 Local Setup

Start Kafka

Create topic:
kafka-topics.bat --create \
--topic post-topic \
--partitions 1 \
--replication-factor 1 \
--bootstrap-server localhost:9092
Start MySQL and create stored procedure

Run Spring Boot application

Call:http://localhost:8080/new-posts

**Author:**

Built as a learning + interview-ready project demonstrating modern backend architecture using Spring Boot, Kafka, and MySQL.
