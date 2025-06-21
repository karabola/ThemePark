CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE provinces (
    Id_province UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(255) NOT NULL
);
INSERT INTO provinces (id_province, name) VALUES
  ('11111111-1111-1111-1111-111111111111','śląskie'),
  ('22222222-2222-2222-2222-222222222222','mazowieckie');

 CREATE TABLE cities (
    id_city UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(255) NOT NULL,
    id_province UUID NOT NULL,
    CONSTRAINT fk_province FOREIGN KEY (id_province) REFERENCES provinces(id_province)
);
INSERT INTO cities (id_city, name, id_province) VALUES
  ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Katowice', '11111111-1111-1111-1111-111111111111'),
  ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Zator', '11111111-1111-1111-1111-111111111111'),
  ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Warszawa', '22222222-2222-2222-2222-222222222222');

CREATE TABLE parks (
    id_park UUID PRIMARY KEY DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(255) NOT NULL,
    id_city UUID NOT NULL,
    id_province UUID NOT NULL,
    description VARCHAR(1000),
    imagesPaths TEXT[],
    CONSTRAINT fk_city FOREIGN KEY (id_city) REFERENCES cities(id_city),
    CONSTRAINT fk_province FOREIGN KEY (id_province) REFERENCES provinces(id_province)
);


INSERT INTO parks (
  id_park, name, id_city, id_province, description, imagesPaths
) VALUES
  (
    'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
    'Legendia',
    'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    '11111111-1111-1111-1111-111111111111',
    'Jeden z największych parków miejskich w Europie Środkowej.',
    ARRAY['/images/Legendia1.jpg']
  ),
  (
    'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
    'Zatorland',
    'aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    '11111111-1111-1111-1111-111111111111',
    'Zabytkowy park z pałacem na wodzie, ulubione miejsce spacerowiczów w Warszawie.',
    ARRAY['/images/Zatorland1.jpg', '/images/Zatorland2.jpg']
  );
