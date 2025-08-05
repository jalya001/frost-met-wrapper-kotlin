# frost-met-wrapper-kotlin
A decent but not good API wrapper for frost.met.no / frost-beta.met.no for fetching observations averaged by month. It is suboptimal and not fully accurate in terms of estimation, but it is better than simply going by the nearest station, and always succeeds at providing measurements in Norway.

## Instructions
1. Get your basic authentication key from Frost and put it in FrostApi.kt basicAuth.
2. Use fetchFrostData like in the provided Ktor server example.

We also have fetchRimData for certain technicalities, using rim.k8s.met.no.

## Theory
Perhaps I'll translate the short whitepaper I wrote on this one day...

## Documentation
I won't write any, and I don't recommend looking into the code, since this project is going to be superseded by [Frost Met Wrapper C++](https://github.com/jalya001/frost-met-wrapper).
