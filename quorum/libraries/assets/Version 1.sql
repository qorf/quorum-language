SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
--
-- Database: `stefika_sodbeans_users`
--

-- --------------------------------------------------------

--
-- Table structure for table `sodbeans_users`
--

CREATE TABLE IF NOT EXISTS `sodbeans_users` (
  `username` varchar(255) NOT NULL,
  `password` char(40) NOT NULL,
  `reset_password_key` char(40) DEFAULT NULL,
  `reset_password_expire` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
