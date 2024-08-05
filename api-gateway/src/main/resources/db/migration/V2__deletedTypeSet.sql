ALTER TABLE `cash_item`
    MODIFY COLUMN `deleted` bit NOT NULL DEFAULT 0;
ALTER TABLE `cash_project`
    MODIFY COLUMN `deleted` bit NOT NULL DEFAULT 0;
ALTER TABLE `core_user_info`
    MODIFY COLUMN `deleted` bit NOT NULL DEFAULT 0;
ALTER TABLE `core_account`
    MODIFY COLUMN `deleted` bit NOT NULL DEFAULT 0;