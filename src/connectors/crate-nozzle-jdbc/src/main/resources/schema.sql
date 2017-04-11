DROP TABLE IF EXISTS enveloppe;
CREATE TABLE enveloppe (
  id string,
  origin string,
  eventType string,
  deployment string,
  job string,
  idx string,
  ipaddress string,
  metricName string,
  metricUnit string,
  metricValue double,
  timestamp TIMESTAMP ,
  httpStart int,
  httpStop int,
  httpStartStop int
);
