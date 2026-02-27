# AI Expense Analyzer

AI-powered Expense Management Backend built using Spring Boot and MongoDB.

## 🚀 Features

- Create, update, delete expenses
- MongoDB database integration
- AI-generated spending summary
- CSV file upload for bulk expenses
- RESTful API architecture
- Layered architecture (Controller → Service → Repository)

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Data MongoDB
- Maven
- MongoDB
- REST APIs
- Postman (API testing)

## 📂 Project Architecture

controller → Handles API requests  
service → Business logic  
repository → Database operations  
entity → MongoDB models

## 📌 API Endpoints

### Create Expense
POST /api/expenses

```json
{
  "title": "Groceries",
  "category": "Food",
  "amount": 25.5
}