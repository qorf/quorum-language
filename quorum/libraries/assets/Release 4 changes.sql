SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

ALTER TABLE `run_code` ADD UUID varchar(100);
ALTER TABLE `run_code` ADD IP varchar(15);
ALTER TABLE `run_code` ADD slidenr TINYINT;
ALTER TABLE `run_code` ADD pagenr TINYINT;