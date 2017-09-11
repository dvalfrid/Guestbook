Gastolibro
===========

[![Build Status](https://travis-ci.org/dvalfrid/gastolibro.svg?branch=master)](https://travis-ci.org/dvalfrid/gastolibro)
[![codecov](https://codecov.io/gh/dvalfrid/gastolibro/branch/master/graph/badge.svg)](https://codecov.io/gh/dvalfrid/gastolibro)

Site: http://www.gastolibro.com/


get /entries?amount=(AMOUNT)&id=(MOST-RECENT-ENTRY)   -> List of Entries

MOST-RECENT-ENTRY = 0 if no entry have been recieved.

Json Structure of Entry

{
  id: Int,
  title: String,
  text: String,
  time: String, YY/MM/DD
  name: String,
  email: String,
  country: String,
  city: String
}



post /entries         -> One Entry

Same Structure as above, minus id, you'll have to assign id.
