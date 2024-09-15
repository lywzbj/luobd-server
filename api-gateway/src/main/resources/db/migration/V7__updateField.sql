ALTER TABLE `luobd`.`core_account` 
ADD COLUMN `verified` bit(1) NULL DEFAULT b'0' AFTER `updateUserId`;
ALTER TABLE `luobd`.`core_user_role` 
CHANGE COLUMN `userInfoId` `accountId` bigint(20) NOT NULL AFTER `deleted`;