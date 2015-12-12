# meep design

Like Twitter's desktop client, Meep will use vertical columns. However, unlike that client, these will actually be [Miller columns](https://en.wikipedia.org/wiki/Miller_columns), such as this:

```
+----+----+----+----+----+----+----+
| C1 | C2 | C3 | C4 | C5 | .. | CN |
|----|----|----|----|----|----|----|
|    |    |    |    |    |    |    |
|    |    |    |    |    |    |    |
...                                |
+----+----+----+----+----+----+----+
```

Upon loading the application, only the first column will display its contents. With the first account in that column being selected, the next columns contents will be displayed.

If There is only one account defined, then the first column will not have the list of accounts, but rather the list of views for that one account.

The columns in the above diagram will be the following:

* C1 = List of accounts
* C2 = List of views (searches, notifications, home, etc.) defined for the selected
       account
* C3 = entities in the timeline for the selected view
* C4 = Tweet meta data
    - Large view of the text, displaying any linked/attached mesia
    - Location data
    - Client name
    - "Replies": clicking will display the timeline of tweets that replied to this one
    - "Retweets": clicking will display users who retweeted
    - "Liked": clicking will display users who liked
* C5 = Context column; examples
    - Timeline of tweet replied:
    - List of Users: clicking will show user view in next column
* C6 and above = further drill-down
