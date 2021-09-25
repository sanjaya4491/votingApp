use
srisal;


drop table if exists CandidateBallotRace;
drop table if exists VoterChoiceBallotRace;
drop table if exists BallotRace;
drop table if exists BallotIssue;
drop table if exists BallotItem;
drop table if exists VoterBallot;
drop table if exists VoterChoice;
drop table if exists Ballot;
drop table if exists Voter;
drop table if exists Auditor;
drop table if exists ApplicationUser;
drop table if exists Candidate;
drop table if exists Person;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;

create table Country
(
    countryId int          not null primary key auto_increment,
    country   varchar(255) not null
);
insert into Country (Country)
values ("USA"),
       ("Tk"),
       ("Mexico"),
       ("Canada");

select *
from Country;

create table State
(
    stateId   int          not null primary key auto_increment,
    state     varchar(255) not null,
    countryId int          not null,
    foreign key (countryId) references Country (countryId)
);

insert into State (state, countryId)
values ("NE", (select countryId from Country where country = "USA")),
       ("TK", (select countryId from Country where country = "Tk")),
       ("la rana", (select countryId from Country where country = "Mexico")),
       ("london", (select countryId from Country where country = "Canada"));

select *
from State;


create table Address
(
    addressId int          not null primary key auto_increment,
    street    varchar(255) not null,
    city      varchar(255) not null,
    zipCode   varchar(255) not null,
    stateId   int          not null,
    foreign key (stateId) references State (stateId)
);

insert into Address (street, city, zipCode, stateId)
values ("21 Peninsula Lane", "Astoria", "11102", (select stateId from State where state = "NE")),
       ("8 Old Greenview Ave", "Sylvania", "43560", (select stateId from State where state = "TK")),
       ("598 W. Charles Lane", "Fremont", "55033", (select stateId from State where state = "la rana")),
       ("7401 Coffee Dr", "Shelbyville", "37160", (select stateId from State where state = "NE")),
       ("245 West Westminster Drive", "Sugar Land", "77478", (select stateId from State where state = "TK")),
       ("9541 Cardinal Rd", "Westminster", "21157", (select stateId from State where state = "la rana")),
       ("9537 East Edgewater St", "Medina", "44256", (select stateId from State where state = "london"));

select *
from Address;



create table Person
(
    personId   int          not null primary key auto_increment,
    personCode varchar(255) unique key not null,
    firstName  varchar(255) not null,
    lastName   varchar(255) not null
);
select*
from Person;

insert into Person (personCode, firstName, lastName)
values ("111222", "Pat", "Mann"),
       ("222333", "Dawn", "KeyKong"),
       ("333444", "Sonic", "None"),
       ("444555", "power", "Puff"),
       ("123456", "Annette", "Apollinaris"),
       ("123423", "Valeriana", "Nkauj"),
       ("123123", "Phoibe", "Emrik"),
       ("354634", "Natale", "Leobwin "),
       ("122321", "Tatiana", "Feidlimid"),
       ("124321", "Virgil", "Eveliina"),
       ("143413", "Lara", "Tacita");


select *
from Person;



create table Candidate
(
    candidateId int not null primary key auto_increment,
    voteCount   int not null default 0,
    personId    int not null,
    foreign key (personId) references Person (personId) on delete cascade
);


insert into Candidate(voteCount, personId)
values (0, (select personId from Person where firstName = "Pat")),
       (0, (select personId from Person where firstName = "Dawn")),
       (0, (select personId from Person where firstName = "Sonic")),
       (0, (select personId from Person where firstName = "power")),
       (69, (select personId from Person where firstName = "Annette")),
       (79, (select personId from Person where firstName = "Valeriana")),
       (89, (select personId from Person where firstName = "Phoibe")),
       (49, (select personId from Person where firstName = "Natale")),
       (39, (select personId from Person where firstName = "Tatiana")),
       (59, (select personId from Person where firstName = "Virgil")),
       (49, (select personId from Person where firstName = "Lara"));

select *
from Candidate;
Select *
from Person;

create table ApplicationUser
(
    applicationUserId int          not null primary key auto_increment,
    email             varchar(255) not null unique key,
    pwd               varchar(255) not null,
    personId          int          not null,
    foreign key (personId) references Person (personId) on delete cascade
);

insert into ApplicationUser(email, pwd, personId)
values ("jaren90@gmail.com", "pAveNTar", (select personId from Person where firstName = "Annette")),
       ("xhuel@sawayn.net", "UrgENtRE", (select personId from Person where firstName = "Valeriana")),
       ("gladyce68@yahoo.com", "BleMoLch", (select personId from Person where firstName = "Phoibe")),
       ("keegan22@yahoo.com", "ARePtchE", (select personId from Person where firstName = "Tatiana")),
       ("vonrueden.lelia@yahoo.com", "CEPSIoNe", (select personId from Person where firstName = "Natale")),
       ("doyle.myrtie@mueller.info", "uTrANdRo ", (select personId from Person where firstName = "Virgil")),
       ("lue34@yahoo.com", "ncHImeWS", (select personId from Person where firstName = "Lara "));


select *
from ApplicationUser;



create table Auditor
(
    auditorId         int not null primary key auto_increment,
    personId          int not null,
    applicationUserId int not null,
    foreign key (personId) references Person (personId) on delete cascade,
    foreign key (applicationUserId) references ApplicationUser (applicationUserId) on delete cascade
);

insert into Auditor(personId, applicationUserId)
values ((select personId from ApplicationUser where email = "doyle.myrtie@mueller.info"),
        (select applicationUserId from ApplicationUser where email = "doyle.myrtie@mueller.info"));

select *
from Auditor;


create table Voter
(
    voterId           int not null primary key auto_increment,
    personId          int not null,
    applicationUserId int not null,
    addressId         int not null,
    foreign key (personId) references Person (personId) on delete cascade,
    foreign key (applicationUserId) references ApplicationUser (applicationUserId) on delete cascade,
    foreign key (addressId) references Address (addressId) on delete cascade
);

insert into Voter(personId, applicationUserId, addressId)
values ((select personId from ApplicationUser where email = "jaren90@gmail.com"),
        (select applicationUserId from ApplicationUser where email = "jaren90@gmail.com"),
        (select addressId from Address where zipCode = "11102"));

insert into Voter(personId, applicationUserId, addressId)
values ((select personId from ApplicationUser where email = "xhuel@sawayn.net"),
        (select applicationUserId from ApplicationUser where email = "xhuel@sawayn.net"),
        (select addressId from Address where zipCode = "43560"));

insert into Voter(personId, applicationUserId, addressId)
values ((select personId from ApplicationUser where email = "gladyce68@yahoo.com"),
        (select applicationUserId from ApplicationUser where email = "gladyce68@yahoo.com"),
        (select addressId from Address where zipCode = "55033"));

insert into Voter(personId, applicationUserId, addressId)
values ((select personId from ApplicationUser where email = "keegan22@yahoo.com"),
        (select applicationUserId from ApplicationUser where email = "keegan22@yahoo.com"),
        (select addressId from Address where zipCode = "37160"));

insert into Voter(personId, applicationUserId, addressId)
values ((select personId from ApplicationUser where email = "vonrueden.lelia@yahoo.com"),
        (select applicationUserId from ApplicationUser where email = "vonrueden.lelia@yahoo.com"),
        (select addressId from Address where zipCode = "77478"));



select *
from Address;
select *
from ApplicationUser;
select *
from Voter;


create table Ballot
(
    ballotId    int          not null primary key auto_increment,
    ballotCode  varchar(255) not null,
    dateandTime datetime,
    personId    int          not null,
    foreign key (personId) references Person (personId) on delete cascade
);

insert into Ballot (ballotCode, dateandTime, personId)
values ("abcdef", "2021-09-12 12:11:00",
        (select personId from ApplicationUser where email = "jaren90@gmail.com"));


insert into Ballot (ballotCode, dateandTime, personId)
values ("bbcdef", "2021-09-12 12:12:00",
        (select personId from ApplicationUser where email = "xhuel@sawayn.net"));

insert into Ballot (ballotCode, dateandTime, personId)
values ("bbccde", "2021-09-12 12:13:00",
        (select personId from ApplicationUser where email = "gladyce68@yahoo.com"));

insert into Ballot (ballotCode, dateandTime, personId)
values ("bbccdd", "2021-09-12 12:14:00",
        (select personId from ApplicationUser where email = "keegan22@yahoo.com"));

insert into Ballot (ballotCode, dateandTime, personId)
values ("aabbcc", "2021-09-12 12:15:00",
        (select personId from ApplicationUser where email = "vonrueden.lelia@yahoo.com"));

select *
from Ballot;

create table VoterChoice
(
    voterChoiceId int          not null primary key auto_increment,
    choice        varchar(255) not null,
    voterId       int          not null,
    foreign key (voterId) references Voter (voterId) on delete cascade

);

insert into VoterChoice(choice, voterId)
values ("wedid;", 1);
insert into VoterChoice(choice, voterId)
values ("aproto", 2);
insert into VoterChoice(choice, voterId)
values ("solti", 3);
insert into VoterChoice(choice, voterId)
values ("wedid", 4);
insert into VoterChoice(choice, voterId)
values ("aproto", 4);

select *
from VoterChoice;



create table VoterBallot
(
    voterBallotId int not null primary key auto_increment,
    voterId       int not null,
    ballotId      int not null,
    foreign key (voterId) references Voter (voterId) on delete cascade,
    foreign key (ballotId) references Ballot (ballotId) on delete cascade
);

insert into VoterBallot (voterId, ballotId)
values ((select voterId
         from Voter
         where personId = (select personId from ApplicationUser where email = "jaren90@gmail.com")),
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "jaren90@gmail.com")));

insert into VoterBallot (voterId, ballotId)
values ((select voterId
         from Voter
         where personId = (select personId from ApplicationUser where email = "xhuel@sawayn.net")),
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "xhuel@sawayn.net")));

insert into VoterBallot (voterId, ballotId)
values ((select voterId
         from Voter
         where personId = (select personId from ApplicationUser where email = "gladyce68@yahoo.com")),
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "gladyce68@yahoo.com")));

insert into VoterBallot (voterId, ballotId)
values ((select voterId
         from Voter
         where personId = (select personId from ApplicationUser where email = "keegan22@yahoo.com")),
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "keegan22@yahoo.com")));

insert into VoterBallot (voterId, ballotId)
values ((select voterId
         from Voter
         where personId = (select personId from ApplicationUser where email = "vonrueden.lelia@yahoo.com")),
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "vonrueden.lelia@yahoo.com")));

select *
from VoterBallot;
select *
from Voter;



create table BallotItem
(
    ballotItemId   int          not null primary key auto_increment,
    ballotItemCode varchar(255) not null,
    descrip        varchar(255) not null,
    title          varchar(255) not null,
    ballotId       int          not null,
    foreign key (ballotId) references Ballot (ballotId) on delete cascade
);

insert into BallotItem (ballotItemCode, descrip, title, ballotId)
values ("xxxxxx", "This is a ballot for Mayoral and presidential(2020)", "Mayoral and presidential Ballot",
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "jaren90@gmail.com ")));

insert into BallotItem (ballotItemCode, descrip, title, ballotId)
values ("xxxxxa", "this is stupid, idk what to say", "Random title",
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "xhuel@sawayn.net")));

insert into BallotItem (ballotItemCode, descrip, title, ballotId)
values ("xxxxxb", "this is stupid, idk what to say", "Random title",
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "gladyce68@yahoo.com")));

insert into BallotItem (ballotItemCode, descrip, title, ballotId)
values ("xxxxxc", "this is stupid, idk what to say", "Random title",
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "keegan22@yahoo.com")));

insert into BallotItem (ballotItemCode, descrip, title, ballotId)
values ("xxxxxd", "this is stupid, idk what to say", "Random title",
        (select ballotId
         from Ballot
         where personId = (select personId from ApplicationUser where email = "vonrueden.lelia@yahoo.com")));

select *
from BallotItem;

create table BallotIssue
(
    BallotIssueId int not null primary key auto_increment,
    yesCount      int default 0,
    noCount       int default 0,
    ballotItemId  int not null,
    foreign key (ballotItemId) references BallotItem (ballotItemId) on delete cascade
);

insert into BallotIssue(yesCount, noCount, ballotItemId)
values (0, 0, 1);

insert into BallotIssue(yesCount, noCount, ballotItemId)
values (0, 0, 2);

insert into BallotIssue(yesCount, noCount, ballotItemId)
values (0, 0, 3);

insert into BallotIssue(yesCount, noCount, ballotItemId)
values (0, 0, 4);



select *
from BallotIssue;


create table BallotRace
(
    ballotRaceId    int not null primary key auto_increment,
    maxNumOfChoices int not null,
    ballotItemId    int null,
    foreign key (ballotItemId) references BallotItem (ballotItemId) on delete cascade
);

insert into BallotRace(maxNumOfChoices, ballotItemId)
values (2, 1);


select *
from BallotRace;


create table VoterChoiceBallotRace
(
    voterChoiceBallotRaceId int not null primary key auto_increment,
    voterChoiceId           int not null,
    ballotRaceId            int not null,
    foreign key (voterChoiceId) references VoterChoice (voterChoiceId) on delete cascade,
    foreign key (ballotRaceId) references BallotRace (ballotRaceId) on delete cascade
);

insert into VoterChoiceBallotRace(voterChoiceId, ballotRaceId)
values (1, 1);


create table CandidateBallotRace
(
    candidateBallotRaceId int not null primary key auto_increment,
    candidateId           int not null,
    ballotRaceId          int not null,
    voteCount             int not null,
    foreign key (candidateId) references Candidate (candidateId) on delete cascade,
    foreign key (ballotRaceId) references BallotRace (ballotRaceId) on delete cascade
);


insert into CandidateBallotRace(candidateId, ballotRaceId, voteCount)
values (1, 1, 0);
insert into CandidateBallotRace(candidateId, ballotRaceId, voteCount)
values (2, 1, 0);
insert into CandidateBallotRace(candidateId, ballotRaceId, voteCount)
values (3, 1, 0);
insert into CandidateBallotRace(candidateId, ballotRaceId, voteCount)
values (4, 1, 0);



select*
from CandidateBallotRace;
select*
from VoterBallot;

/*
SELECT applicationUserId FROM ApplicationUser WHERE personId = 1
Set @total = (SELECT voteCount FROM Candidate WHERE candidateId = 1);
UPDATE Candidate SET voteCount =  @total+ 1 WHERE candidateId = 1;

select *from Candidate;
*/


select *
from Person;
select *
from Auditor;
select *
from ApplicationUser;
select *
from Voter;
select *
from BallotItem;
select *
from Candidate;
select *
from BallotRace;



