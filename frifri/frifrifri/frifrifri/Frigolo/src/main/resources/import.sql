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
insert into TYPECAPTEUREJBENTITY  (idTypeCapteur, Type) values (1, 'Qualite d''air')
insert into TYPECAPTEUREJBENTITY  (idTypeCapteur, Type) values (2, 'Temperature')
insert into TYPECAPTEUREJBENTITY  (idTypeCapteur, Type) values (3, 'Lumiere')

insert into FRIGIDAIREEJBENTITY (idFrigidaire, nomFrigidaire) values (51, 'cuisine')
insert into FRIGIDAIREEJBENTITY (idFrigidaire, nomFrigidaire) values (52, 'SDB')


insert into CAPTEURNUMERIQUEEJBENTITY  (idCapteur, nomCapteur, TypeCapteur_idTypeCapteur, Frigidaire_idFrigidaire) values (1, 'Voltmetre', 1, 52)
insert into CAPTEURNUMERIQUEEJBENTITY  (idCapteur, nomCapteur, TypeCapteur_idTypeCapteur, Frigidaire_idFrigidaire) values (2, 'Amperemetre', 2, 52)

insert into CAPTEURLOGIQUEEJBENTITY  (idCapteur, nomCapteur, TypeCapteur_idTypeCapteur, Frigidaire_idFrigidaire) values (3, 'ouverture de frigo', 3, 51)
insert into CAPTEURLOGIQUEEJBENTITY  (idCapteur, nomCapteur, TypeCapteur_idTypeCapteur, Frigidaire_idFrigidaire) values (4, 'roger', 3, 52)


insert into UniteEJBEntity (idUnite, unite) values (11, 'V')
insert into UniteEJBEntity (idUnite, unite) values (12, 'A')

insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (21, '2011-01-01 13:25:15.235', 5,1, 11)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (22, '2012-01-01 13:25:15.235', 5,1, 11)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (23, '2013-01-01 13:25:15.235', 5,2, 12)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (24, '2014-01-01 13:25:15.235', 5,1, 11)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (25, '2015-01-01 13:25:15.235', 5,1, 11)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (26, '2016-01-01 13:25:15.235', 5,2, 12)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (27, '2017-01-01 13:25:15.235', 5,2, 12)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (28, '2018-01-01 13:25:15.235', 5,1, 11)
insert into MesureNumeriqueEJBEntity (idMesure, timeStamp, valeur, capteurNumerique_idCapteur, Unite_idUnite) values (29, '2019-01-01 13:25:15.235', 5,1, 11)


insert into MesureLogiqueEJBEntity (idMesure, timeStamp, etat, capteurLogique_idCapteur) values (41, '2019-01-01 13:25:15.235', true,3)
insert into MesureLogiqueEJBEntity (idMesure, timeStamp, etat, capteurLogique_idCapteur) values (42, '2018-01-01 13:25:15.235', false,4)
insert into MesureLogiqueEJBEntity (idMesure, timeStamp, etat, capteurLogique_idCapteur) values (43, '2017-01-01 13:25:15.235', true,4)
insert into MesureLogiqueEJBEntity (idMesure, timeStamp, etat, capteurLogique_idCapteur) values (44, '2016-01-01 13:25:15.235', true,4)
insert into MesureLogiqueEJBEntity (idMesure, timeStamp, etat, capteurLogique_idCapteur) values (45, '2015-01-01 13:25:15.235', false,3)
insert into MesureLogiqueEJBEntity (idMesure, timeStamp, etat, capteurLogique_idCapteur) values (46, '2014-01-01 13:25:15.235', true,3)
insert into MesureLogiqueEJBEntity (idMesure, timeStamp, etat, capteurLogique_idCapteur) values (47, '2013-01-01 13:25:15.235', true,3)



