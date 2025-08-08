```sql
create table users (
	uid varchar(32) default upper(replace(gen_random_uuid()::text,'-'::text,''::text)) not null constraint uid_pk primary key,
	username VARCHAR(255),
	user_metadata jsonb
);
```
