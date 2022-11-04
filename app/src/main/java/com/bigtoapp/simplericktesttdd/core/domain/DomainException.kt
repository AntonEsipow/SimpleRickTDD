package com.bigtoapp.simplericktesttdd.core.domain

abstract class DomainException: IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException: DomainException()