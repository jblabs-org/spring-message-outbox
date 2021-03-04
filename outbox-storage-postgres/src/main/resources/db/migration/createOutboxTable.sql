CREATE TABLE message_outbox
(
    message_id text NOT NULL,
    message_type text,
    aggregate_name text,
    aggregate_id text,
    destination text,
    payload text,
    created_at timestamp with time zone,
    is_published boolean,
    CONSTRAINT message_outbox_pkey PRIMARY KEY (message_id)
);

CREATE INDEX idx_message_outbox_created_at
ON message_outbox(created_at);