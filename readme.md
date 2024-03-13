# OSGI Assignment - Library Management System

## Overview

The Library Management System is built on the OSGi framework and consists of four bundles:

- **librarymanagerconsumer**: Interacts with library management services.
- **librarymanagerpublisher**: Publishes library management services.
- **membershipconsumer**: Interacts with membership services.
- **membershippublisher**: Publishes membership services.
- **librarydb**: Handles database operations.

## Usage

Deploy the bundles in an OSGi framework and interact with the system using consumer bundles (`librarymanagerconsumer` and `membershipconsumer`). Dependencies include the OSGi framework and MySQL database.
