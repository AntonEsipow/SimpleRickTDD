package com.bigtoapp.simplericktesttdd.details.core.domain

abstract class DomainException: IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException: DomainException()