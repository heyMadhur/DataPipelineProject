# 🚀 DataPipelineProject: Real-time Wikimedia Data Pipeline

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://openjdk.org/projects/jdk/17/)
[![Apache Camel](https://img.shields.io/badge/Apache_Camel-3.20.0-orange)](https://camel.apache.org/)
[![Kafka](https://img.shields.io/badge/Apache_Kafka-3.3.1-000)](https://kafka.apache.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0.6-brightgreen)](https://spring.io/projects/spring-boot)

A modular real-time data pipeline that ingests Wikimedia event streams, processes them through Kafka, and stores them in both database and CSV formats with a queryable REST API.

## 🌟 Features

- **Real-time ingestion** of Wikimedia event streams
- **Dual storage** in H2 database and CSV files
- **Dynamic REST API** with filtering capabilities
- **Modular architecture** with clear separation of concerns
- **Scalable design** using Apache Kafka

## 🧩 Architecture

```mermaid
┌───────────────────┐      ┌──────────────┐      ┌──────────┐
│ Wikimedia Stream  │─────▶│ Camel Ingestor├─────▶│  Kafka   │
└───────────────────┘      └──────────────┘      └────┬─────┘                                     │
                                          │          │
                             ┌────────────▼┐  ┌──────▼───────┐
                             │DB Consumer  │  │ CSV Consumer │
                             │(Spring Boot)│  │(Spring Boot) │
                             └──────┬──────┘  └──────┬───────┘
                                    │                │
                           ┌───────▼────┐     ┌─────▼─────┐
                           │ H2 Database│     │ CSV Files │
                           └──────┬─────┘     └───────────┘
                                  │
                           ┌──────▼─────┐
                           │ REST API   │
                           │ w/ Filters │
                           └──────┬─────┘
                                  │
                           ┌──────▼─────┐
                           │ End User   │
                           └────────────┘
```


## 🛠️ Technology Stack

| Component         | Technology                          |
|-------------------|-------------------------------------|
| **Ingestion**     | Apache Camel 3.20.0                 |
| **Messaging**     | Apache Kafka 3.3.1                  |
| **Framework**     | Spring Boot 3.0.6                   |
| **Database**      | H2 Database                         |
| **API**           | Spring Web MVC                      |
| **Build Tool**    | Maven                               |
| **Java Version**  | 17+                                 |

## 📂 Project Structure

```bash
DataPipelineProject/
├── camel-ingestor       # Wikimedia event ingestion with Camel
├── kafka-consumer-db    # Kafka consumer for database storage
├── kafka-consumer-csv   # Kafka consumer for CSV output
├── rest-db-handler      # REST API for querying stored data
├── database             # Generated data files
└── src                  # Main application entry point
```

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Apache Kafka 3.3.1
- Maven 3.8+

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/DataPipelineProject.git
   cd DataPipelineProject
   ```

2. **Start Kafka:**
   ```bash
   # Start Zookeeper
   bin/zookeeper-server-start.sh config/zookeeper.properties

   # Start Kafka
   bin/kafka-server-start.sh config/server.properties
   ```

3. **Build and run modules:**
   ```bash
   # Build all modules
   mvn clean install
   
   # Run Camel ingestor
   cd camel-ingestor
   mvn spring-boot:run
   
   # Run DB consumer (in new terminal)
   cd kafka-consumer-db
   mvn spring-boot:run
   
   # Run CSV consumer (in new terminal)
   cd kafka-consumer-csv
   mvn spring-boot:run
   
   # Run REST API (in new terminal)
   cd rest-db-handler
   mvn spring-boot:run
   ```

4. **Access the REST API:**
   ```
   GET http://localhost:8080/api
   ```

### Example API Queries
```bash
# Basic Fetch
curl http://localhost:8080/api/changes

# Filter Query
curl http://localhost:8080/api/changes?user.equals=EyobAbebe10&domain.equals=commons.wikimedia.org&title.contains=Category

# Filter+Pagination
curl http://localhost:8080/api/changes?bot.equals=false&user.equals=EyobAbebe10&page=0&sort=title,asc
```

## 🔮 Future Roadmap

- [ ] Add batch processing with Apache Spark
- [ ] Implement cloud deployment (AWS/Azure)
- [ ] Add real-time dashboards with Grafana
- [ ] Implement data validation layer
- [ ] Add Docker support for containerization

## 🤝 Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
Built with ❤️ by Madhur Gupta - Happy data pipelining! 🚀
