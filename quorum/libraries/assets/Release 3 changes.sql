SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `library_submissions` (
  `library_id` varchar(40) NOT NULL,
  `library_name` varchar(255) NOT NULL,
  `uploader_username` varchar(255) NOT NULL,
  `author_name` varchar(255) NOT NULL,
  `library_description` varchar(2000) NOT NULL,
  `usage_instructions` varchar(2000) NOT NULL,
  `submission_url` varchar(255) NOT NULL,
  `supplementary_files_url` varchar(255) NOT NULL,
  `public_display` int(1) NOT NULL,
  `status` varchar(40) NOT NULL,
  `date_submitted` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library_submission_reviews` (
  `library_id` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `feedback` varchar(3000) NOT NULL,
  `confidential_feedback` varchar(3000) NOT NULL,
  `status` varchar(30) NOT NULL,
  `technical_rating` int(11) NOT NULL,
  `usability_rating` int(11) NOT NULL,
  `date_reviewed` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `library_submission_reviewer` (
  `library_id` varchar(40) NOT NULL,
  `username` varchar(255) NOT NULL,
  `review_submitted` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `badges` (
  `username` varchar(255) NOT NULL,
  `badge` varchar(50) NOT NULL,
  `badge_type` varchar(50) NOT NULL,
  `date_received` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `sodbeans_users` ADD administrator int(1) DEFAULT 0;
ALTER TABLE `sodbeans_users` ADD first_name varchar(100);
ALTER TABLE `sodbeans_users` ADD last_name varchar(100);