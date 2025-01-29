# 🌟 **SnipSnap URL Shortener** 🚀
Welcome to **SnipSnap**, your one-stop solution to transform lengthy URLs into **short, secure, and trackable links**. Whether you're sharing a personal link or managing professional campaigns, we empower you with real-time analytics and seamless integrations.
## 📌 **Key Features**
✨ **Elegant URL Shortening:** Turn your long URLs into shorter, memorable, and shareable formats.
🔒 **Role-Based Authentication:** Robust user authentication and authorization via **JWT**.
📊 **Click Tracking & Analytics:** Monitor clicks, track timestamps, and analyze aggregated statistics over time.
🧩 **RESTful API Access:** Integrate with your systems for automated URL shortening and insights.
🎯 **Custom Admin Dashboard:** Easily manage user-generated links and gain business insights.
💡 **Frontend Built with React & Next.js:** Rich user experience with modern styling and animations.
🎨 **Stunning Visuals:** Built with **Three.js** for immersive and interactive 3D animations.
## 🛠️ **Tech Stack**

| **Backend** | **Frontend** | **Database** | **Security** | **Build Tool** | **Third-Party Libraries** |
| --- | --- | --- | --- | --- | --- |
| Spring Boot | React + Next.js (UI Layer) | PostgreSQL/MySQL | Spring Security + JWT | Maven | Three.js, Framer Motion |
## ✨ **Key Features Added**
- **Enhanced Click Analytics:**
    - Track clicks for each `shortURL` and retrieve data in specific date ranges.
    - Grouped daily statistics for easier analysis.

- **DTO Implementation for Analytics:**
    - Added **DTO classes** (`ClickEventDTO`, `UrlMappingDTO`) for clean data transfer across layers.
    - Custom REST endpoints to link analytics with visual dashboards.

- **New Repository Methods:**
    - Custom queries added to `ClickEventRepository` for fetching analytics data.
    - Support for a flexible date range to derive click statistics quickly.

## 🚀 **Getting Started**
Follow these steps to set up and run the project locally:
### 📂 **1. Clone the Project**
``` bash
git clone https://github.com/your-username/snipsnap-url-shortener.git
cd snipsnap-url-shortener
```
### 🛠️ **2. Install Backend Dependencies**
Make sure **Java 21**, **Maven**, and your preferred database (PostgreSQL/MySQL) are installed.
Configure your database in the `application.properties` file under `src/main/resources`:
``` properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/snipsnap
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```
### 🎨 **3. Set Up Frontend**
Navigate to the frontend directory:
``` bash
cd frontend
npm install
npm run dev
```
Access the **React + Next.js UI** on **[http://localhost:3000]()**.
### 🏃 **4. Run the Spring Boot Backend**
Run the backend application:
``` bash
./mvnw spring-boot:run
```
By default, the backend is hosted on **[http://localhost:8080]()**.
## 🌐 **API Endpoints**
### 📎 **URL Shortening**

| **Method** | **Endpoint** | **Description** |
| --- | --- | --- |
| POST | `/api/url/shorten` | Shorten a long URL |
| GET | `/api/url/getAllUrl` | Retrieve all shortened URLs for a user |
| DELETE | `/api/url/{id}` | Delete a short URL by its ID |
### 📊 **Click Events Analytics**

| **Method** | **Endpoint** | **Description** |
| --- | --- | --- |
| GET | `/api/url/analytics/{shortURL}` | Fetch daily analytics data for a URL |
| GET | `/api/url/analytics/{shortURL}?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd` | Analytics filtered by a date range |
| GET | `/api/clicks/{urlId}` | Fetch raw click events for an individual URL |
## 📊 **Built-in Analytics**
Recorded statistics provide insightful data, such as:
- **Daily Click Counts**: Grouped click events by date.
- **Custom Date Ranges**: Filter analytics between specific timeframes for better campaign tracking.
- **Event Metadata**: Timestamps and mapping to original URLs.

Analytics endpoints serve **frontend dashboards** and REST API use cases.
## 🎨 **User Interface**
The **React** frontend incorporates modern design principles with interactive 3D elements for an engaging experience. The UI is styled with **TailwindCSS** and includes components from libraries like **shadcn/ui**.
**Features include:**

## 🤝 **Contributing**
We ❤️ contributions! Please fork the repository, create a branch, and submit pull requests. Check out our **issues** section for ongoing challenges.
## 🛡️ **License**
Distributed under the [MIT License]().

> Feel free to reach out with feedback or feature suggestions. Let’s make URL shortening simple, secure, and insightful with **SnipSnap**! 🙌
>
