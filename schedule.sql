CREATE TABLE `user` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `username` VARCHAR(100) NOT NULL,
                        `email` VARCHAR(100) NOT NULL UNIQUE,
                        `password` VARCHAR(200) NOT NULL,
                        `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `schedule` (
                            `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                            `title` VARCHAR(200) NOT NULL,
                            `content` TEXT,
                            `user_id` BIGINT NOT NULL,
                            `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
                            `modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES `user`(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
