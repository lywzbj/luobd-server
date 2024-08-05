ALTER TABLE `cash_item`
    ADD COLUMN `createUserId` bigint NULL DEFAULT 0;
ALTER TABLE `cash_item`
    ADD COLUMN `updateUserId` bigint NULL DEFAULT 0;

ALTER TABLE `cash_project`
    ADD COLUMN `createUserId` bigint NULL DEFAULT 0;
ALTER TABLE `cash_project`
    ADD COLUMN `updateUserId` bigint NULL DEFAULT 0;

ALTER TABLE `core_user_info`
    ADD COLUMN `createUserId` bigint NULL DEFAULT 0;
ALTER TABLE `core_user_info`
    ADD COLUMN `updateUserId` bigint NULL DEFAULT 0;

ALTER TABLE `core_account`
    ADD COLUMN `createUserId` bigint NULL DEFAULT 0;
ALTER TABLE `core_account`
    ADD COLUMN `updateUserId` bigint NULL DEFAULT 0;