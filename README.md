### Simple Game Controller

Operates in 2 modes:
## Single-process mode:
the 2 players are running in the same process but in different threads.
The initiator sends 10 messages to the receiver. The receiver echoes back those messages to the initiator amended with the message number.
Both the initiator and receiver terminate afterwards

## Multi-process mode:
Works like the single-process mode, but the 2 players are running in different processes and communicate through sockets on localhost

## To run the game:
$ mvn clean install
A jar with dependencies will be created in the "target" directory
Run the shell script simplegame.sh from its current location with the following arguments
For a single process game:
$ ./simplegame.sh --mode s
For running a multi-process mode initiator player
$ ./simplegame.sh --mode m --player i
For running a multi-process mode receiver player
$ ./simplegame.sh --mode m --player r
For multi-process game, the default port is 5555. You can optionally override the port number if you pass the --port command argument
e.g.
$ ./simplegame.sh --mode m --player i --port 1234
and
$ ./simplegame.sh --mode m --player r --port 1234
Note: Port numbers must be the same for both the initiator and the receiver

## Game Result
You will see the game output on the standard output as logging messages (The initiator player logs the messages it sends and the replies it gets from the receiver)
