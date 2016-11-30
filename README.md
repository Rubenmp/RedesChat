# RedesChat

This is a user-to-user chat app we developed for a class project.

## How does it work?

There's one server and two users. Each user should run a Printer and a Writer. Users write messages from Writer and read them from Printer. You should also run a server somewhere. Whenever users run a Printer or a Writer they're asked the IP of the device where the server is running.

## Message format

This chat has no GUI, so you can send and receive bold, underlined or blink text if your terminal supports ANSI\*. However, we implemented a more convenient way of doing this: You send bold, underlined or blink text \*like this\*, \_like this\_ or \\like this\\.

## Coming soon

You can observe that some of the classes aren't actually being used. That's because they're part of some features we're planning to implement in the next months:

- Some kind of message encryption
- Sending files
- Group chats
- User authentication
- Multiple chats at a time

---

\*If you're using GNOME terminal, blink text probably won't work.
