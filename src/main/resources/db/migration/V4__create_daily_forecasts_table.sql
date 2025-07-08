CREATE TABLE daily_forecasts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL,
    city_id BIGINT,
    recorded_date DATE,
    temperature DOUBLE,
    relative_humidity BIGINT,
    dew_point DOUBLE,
    cloud_cover BIGINT,
    rain DOUBLE,
    weather_code BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (city_id) REFERENCES cities(id)
);