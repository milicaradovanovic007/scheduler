CREATE TABLE `schedule_task` (
                                 `id` int(11) NOT NULL,
                                 `name` varchar(250) NOT NULL,
                                 `recurrency` varchar(30) NOT NULL,
                                 `code` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `schedule_task`
    ADD PRIMARY KEY (`id`);

ALTER TABLE `schedule_task`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

INSERT INTO `schedule_task` (`id`, `name`, `recurrency`, `code`) VALUES
(1, 'Task 1', '0 * * * * *', 'println "Hello World"');