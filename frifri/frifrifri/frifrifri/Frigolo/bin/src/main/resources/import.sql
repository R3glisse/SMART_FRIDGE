--
-- JBoss, Home of Professional Open Source
-- Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
insert into CAPTEURNUMERIQUEEJBENTITY  (idCapteur, nomCapteur) values (10, 'tension')
insert into CAPTEURNUMERIQUEEJBENTITY  (idCapteur, nomCapteur) values (11, 'courant')


insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur) values (10, '2010-01-01 13:25:15.235',6)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (11, '2011-01-01 13:25:15.235', 5,1, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (12, '2012-01-01 13:25:15.235', 5,1, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (13, '2013-01-01 13:25:15.235', 5,2, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (14, '2014-01-01 13:25:15.235', 5,1, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (15, '2015-01-01 13:25:15.235', 5,1, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (16, '2016-01-01 13:25:15.235', 5,2, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (17, '2017-01-01 13:25:15.235', 5,2, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (18, '2018-01-01 13:25:15.235', 5,1, null)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (19, '2019-01-01 13:25:15.235', 5,1, null)