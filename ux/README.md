# Experiment with geospatial imaging

## Pre-requisites

1. Register with Cesium and [obtain access token](https://cesium.com/ion/tokens)
2. Upload an airplane model following [instructions in Steps 5 and 6]((https://cesium.com/docs/tutorials/build-a-flight-tracker/))
3. Replace variables in index.html marked with `REPLACE-FROM-SECRETS` with values from `.secrets` (collected in steps 1 and 2)

Start `server/` application to provide coordinates data

```bash
cd server
./mvnw spring-boot:run
```

## References

Code is based on [Flight Simulation Example](https://cesium.com/docs/tutorials/build-a-flight-tracker/)
