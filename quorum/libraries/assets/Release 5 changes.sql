ALTER TABLE `run_code` ADD pageURL text;
ALTER TABLE `run_code` ADD google text;
ALTER TABLE `run_code` DROP COLUMN times_requested;
ALTER TABLE `run_code` ADD insertionTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP;