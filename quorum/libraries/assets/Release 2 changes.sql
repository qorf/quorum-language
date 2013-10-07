# Add admin column
ALTER TABLE sodbeans_users ADD COLUMN administrator BIT DEFAULT 0;


# User_submission_vvotes table
CREATE TABLE IF NOT EXISTS `user_submission_votes` (
  `username` varchar(255) NOT NULL,
  `library_submission_id` varchar(40) NOT NULL,
  `upvote_or_downvote` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#Library_submissions_table
CREATE TABLE IF NOT EXISTS `library_submissions` (
  `library_id` varchar(40) NOT NULL,
  `library_name` varchar(255) NOT NULL,
  `author_name` varchar(255) NOT NULL,
  `library_description` varchar(2000) NOT NULL,
  `usage_instructions` varchar(2000) NOT NULL,
  `submission_url` varchar(255) NOT NULL,
  `supplementary_files_url` varchar(255) NOT NULL,
  `public_display` bit(1) NOT NULL,
  `status` varchar(40) NOT NULL,
  `date_submitted` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#Library_submission_reviews
CREATE TABLE IF NOT EXISTS `library_submission_reviews` (
  `library_id` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `feedback` varchar(3000) NOT NULL,
  `status` varchar(30) NOT NULL,
  `date_reviewed` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#Library_submission_reviewer
CREATE TABLE IF NOT EXISTS `library_submission_reviewer` (
  `library_id` varchar(40) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;