# todo

## simple architecture

~/.meep/creds.clj - auth into
  * 

~/.meep/columns.clj - define the columns that are displayed in the UI
  * a list of entries
  * each entry has name, type (maps directly to API call to fetch data),
    user, max count (will not put more than max count number of status updates
    in the column)

~/.meep/style.clj - styling info (e.g., column width, font size)
  * 

## Components

* status update
  * retweet button/icon
  * favourite button/icon
  * reply button/icon
  * renders images and links in-line (can disable in style.clj)
  * provide status update metadata in collapsed tree (expand to read)
* column
