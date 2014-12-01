package sigrun.serialization;

import sigrun.common.CoordinateUnitsCode;
import sigrun.common.GainTypeForInstruments;
import sigrun.common.TraceHeader;
import sigrun.common.TraceIdentificationCode;

import java.util.Arrays;

import static sigrun.converters.ByteANumberConverter.*;

public class TraceHeaderReader {
    public final TraceHeaderFormat format;

    public TraceHeaderReader(TraceHeaderFormat format) {
        this.format = format;
    }

    public TraceHeader read(byte[] buffer) {
        TraceHeader traceHeader = new TraceHeader();

        if (format.traceSequenceNumberWLFormat != null)
            traceHeader.setTraceSequenceNumberWL(byteAToInt(buffer, format.traceSequenceNumberWLFormat.posStart));

        if (format.traceSequenceNumberWSFormat != null)
            traceHeader.setTraceSequenceNumberWS(byteAToInt(buffer, format.traceSequenceNumberWSFormat.posStart));

        if (format.originalFieldRecordNumberFormat != null)
            traceHeader.setOriginalFieldRecordNumber(byteAToInt(buffer, format.originalFieldRecordNumberFormat.posStart));

        if (format.traceNumberWOFRFormat != null)
            traceHeader.setTraceNumberWOFR(byteAToInt(buffer, format.traceNumberWOFRFormat.posStart));

        if (format.energySourcePointNumberFormat != null)
            traceHeader.setEnergySourcePointNumber(byteAToInt(buffer, format.energySourcePointNumberFormat.posStart));

        if (format.ensembleNumberFormat != null)
            traceHeader.setEnsembleNumber(byteAToInt(buffer, format.ensembleNumberFormat.posStart));

        if (format.traceNumberWEnsembleFormat != null)
            traceHeader.setTraceNumberWEnsemble(byteAToInt(buffer, format.traceNumberWEnsembleFormat.posStart));

        if (format.traceIdentificationCodeFormat != null)
            traceHeader.setTraceIdentificationCode(TraceIdentificationCode.create(byteAToShort(buffer, format.traceIdentificationCodeFormat.posStart)));

        if (format.numberOfVerticallySummedTracesFormat != null)
            traceHeader.setNumberOfVerticallySummedTraces(byteAToShort(buffer, format.numberOfVerticallySummedTracesFormat.posStart));

        if (format.numberOfHorizontallyStackedTracesFormat != null)
            traceHeader.setNumberOfHorizontallyStackedTraces(byteAToShort(buffer, format.numberOfHorizontallyStackedTracesFormat.posStart));

        if (format.dataUseFormat != null)
            traceHeader.setDataUse(byteAToShort(buffer, format.dataUseFormat.posStart));

        if (format.distanceFromTheCenterOfSPFormat != null)
            traceHeader.setDistanceFromTheCenterOfSP(byteAToInt(buffer, format.distanceFromTheCenterOfSPFormat.posStart));

        if (format.receiverGroupElevationFormat != null)
            traceHeader.setReceiverGroupElevation(byteAToInt(buffer, format.receiverGroupElevationFormat.posStart));

        if (format.surfaceElevationAtSourceFormat != null)
            traceHeader.setSurfaceElevationAtSource(byteAToInt(buffer, format.surfaceElevationAtSourceFormat.posStart));

        if (format.sourceDepthBelowSurfaceFormat != null)
            traceHeader.setSourceDepthBelowSurface(byteAToInt(buffer, format.sourceDepthBelowSurfaceFormat.posStart));

        if (format.datumElevationAtReceiverGroupFormat != null)
            traceHeader.setDatumElevationAtReceiverGroup(byteAToInt(buffer, format.datumElevationAtReceiverGroupFormat.posStart));

        if (format.datumElevationAtSourceFormat != null)
            traceHeader.setDatumElevationAtSource(byteAToInt(buffer, format.datumElevationAtSourceFormat.posStart));

        if (format.waterDepthAtSourceFormat != null)
            traceHeader.setWaterDepthAtSource(byteAToInt(buffer, format.waterDepthAtSourceFormat.posStart));

        if (format.waterDepthAtGroupFormat != null)
            traceHeader.setWaterDepthAtGroup(byteAToInt(buffer, format.waterDepthAtGroupFormat.posStart));

        if (format.scalarForElevationsFormat != null)
            traceHeader.setScalarForElevations(byteAToShort(buffer, format.scalarForElevationsFormat.posStart));

        if (format.scalarForCoordinatesFormat != null)
            traceHeader.setScalarForCoordinates(byteAToShort(buffer, format.scalarForCoordinatesFormat.posStart));

        if (format.sourceXFormat != null)
            traceHeader.setSourceX(byteAToInt(buffer, format.sourceXFormat.posStart));

        if (format.sourceYFormat != null)
            traceHeader.setSourceY(byteAToInt(buffer, format.sourceYFormat.posStart));

        if (format.groupXFormat != null)
            traceHeader.setGroupX(byteAToInt(buffer, format.groupXFormat.posStart));

        if (format.groupYFormat != null)
            traceHeader.setGroupY(byteAToInt(buffer, format.groupYFormat.posStart));

        if (format.coordinateUnitsCodeFormat != null)
            traceHeader.setCoordinateUnitsCode(CoordinateUnitsCode.create(byteAToShort(buffer, format.coordinateUnitsCodeFormat.posStart)));

        if (format.weatheringVelocityFormat != null)
            traceHeader.setWeatheringVelocity(byteAToShort(buffer, format.weatheringVelocityFormat.posStart));

        if (format.subweatheringVelocityFormat != null)
            traceHeader.setSubweatheringVelocity(byteAToShort(buffer, format.subweatheringVelocityFormat.posStart));

        if (format.upholeTimeAtSourceInMsFormat != null)
            traceHeader.setUpholeTimeAtSourceInMs(byteAToShort(buffer, format.upholeTimeAtSourceInMsFormat.posStart));

        if (format.upholeTimeAtGroupInMsFormat != null)
            traceHeader.setUpholeTimeAtGroupInMs(byteAToShort(buffer, format.upholeTimeAtGroupInMsFormat.posStart));

        if (format.sourceStaticCorrectionInMsFormat != null)
            traceHeader.setSourceStaticCorrectionInMs(byteAToShort(buffer, format.sourceStaticCorrectionInMsFormat.posStart));

        if (format.groupStaticCorrectionInMsFormat != null)
            traceHeader.setGroupStaticCorrectionInMs(byteAToShort(buffer, format.groupStaticCorrectionInMsFormat.posStart));

        if (format.totalStaticAppliedInMsFormat != null)
            traceHeader.setTotalStaticAppliedInMs(byteAToShort(buffer, format.totalStaticAppliedInMsFormat.posStart));

        if (format.lagTimeAFormat != null)
            traceHeader.setLagTimeA(byteAToShort(buffer, format.lagTimeAFormat.posStart));

        if (format.lagTimeBFormat != null)
            traceHeader.setLagTimeB(byteAToShort(buffer, format.lagTimeBFormat.posStart));

        if (format.delayRecordingTimeFormat != null)
            traceHeader.setDelayRecordingTime(byteAToShort(buffer, format.delayRecordingTimeFormat.posStart));

        if (format.muteTimeStartFormat != null)
            traceHeader.setMuteTimeStart(byteAToShort(buffer, format.muteTimeStartFormat.posStart));

        if (format.muteTimeEndFormat != null)
            traceHeader.setMuteTimeEnd(byteAToShort(buffer, format.muteTimeEndFormat.posStart));

        if (format.numberOfSamplesFormat != null)
            traceHeader.setNumberOfSamples(byteAToShort(buffer, format.numberOfSamplesFormat.posStart));

        if (format.sampleIntervalInMcsFormat != null)
            traceHeader.setSampleIntervalInMcs(byteAToShort(buffer, format.sampleIntervalInMcsFormat.posStart));

        if (format.gainTypeForInstrumentsFormat != null)
            traceHeader.setGainTypeForInstruments(GainTypeForInstruments.create(byteAToShort(buffer, format.gainTypeForInstrumentsFormat.posStart)));

        if (format.instrumentGainConstantFormat != null)
            traceHeader.setInstrumentGainConstant(byteAToShort(buffer, format.instrumentGainConstantFormat.posStart));

        if (format.instrumentEarlyOrInitialGainFormat != null)
            traceHeader.setInstrumentEarlyOrInitialGain(byteAToShort(buffer, format.instrumentEarlyOrInitialGainFormat.posStart));

        if (format.correlatedFormat != null)
            traceHeader.setCorrelated(byteAToShort(buffer, format.correlatedFormat.posStart));

        if (format.sweepFrequencyAtStartFormat != null)
            traceHeader.setSweepFrequencyAtStart(byteAToShort(buffer, format.sweepFrequencyAtStartFormat.posStart));

        if (format.sweepFrequencyAtEndFormat != null)
            traceHeader.setSweepFrequencyAtEnd(byteAToShort(buffer, format.sweepFrequencyAtEndFormat.posStart));

        if (format.sweepLengthInMillisecondsFormat != null)
            traceHeader.setSweepLengthInMilliseconds(byteAToShort(buffer, format.sweepLengthInMillisecondsFormat.posStart));

        if (format.sweepTypeFormat != null)
            traceHeader.setSweepType(byteAToShort(buffer, format.sweepTypeFormat.posStart));

        if (format.sweepTraceTaperLengthAtStartInMillisecondsFormat != null)
            traceHeader.setSweepTraceTaperLengthAtStartInMilliseconds(byteAToShort(buffer, format.sweepTraceTaperLengthAtStartInMillisecondsFormat.posStart));

        if (format.sweepTraceTaperLengthAtEndInMillisecondsFormat != null)
            traceHeader.setSweepTraceTaperLengthAtEndInMilliseconds(byteAToShort(buffer, format.sweepTraceTaperLengthAtEndInMillisecondsFormat.posStart));

        if (format.taperTypeFormat != null)
            traceHeader.setTaperType(byteAToShort(buffer, format.taperTypeFormat.posStart));

        if (format.aliasFilterFrequencyFormat != null)
            traceHeader.setAliasFilterFrequency(byteAToShort(buffer, format.aliasFilterFrequencyFormat.posStart));

        if (format.aliasFilterSlopeFormat != null)
            traceHeader.setAliasFilterSlope(byteAToShort(buffer, format.aliasFilterSlopeFormat.posStart));

        if (format.notchFilterFrequencyFormat != null)
            traceHeader.setNotchFilterFrequency(byteAToShort(buffer, format.notchFilterFrequencyFormat.posStart));

        if (format.notchFilterSlopeFormat != null)
            traceHeader.setNotchFilterSlope(byteAToShort(buffer, format.notchFilterSlopeFormat.posStart));

        if (format.lowCutFrequencyFormat != null)
            traceHeader.setLowCutFrequency(byteAToShort(buffer, format.lowCutFrequencyFormat.posStart));

        if (format.highCutFrequencyFormat != null)
            traceHeader.setHighCutFrequency(byteAToShort(buffer, format.highCutFrequencyFormat.posStart));

        if (format.lowCutSlopeFormat != null)
            traceHeader.setLowCutSlope(byteAToShort(buffer, format.lowCutSlopeFormat.posStart));

        if (format.highCutSlopeFormat != null)
            traceHeader.setHighCutSlope(byteAToShort(buffer, format.highCutSlopeFormat.posStart));

        if (format.yearDataRecordedFormat != null)
            traceHeader.setYearDataRecorded(byteAToShort(buffer, format.yearDataRecordedFormat.posStart));

        if (format.dayOfYearFormat != null)
            traceHeader.setDayOfYear(byteAToShort(buffer, format.dayOfYearFormat.posStart));

        if (format.hourOfDayFormat != null)
            traceHeader.setHourOfDay(byteAToShort(buffer, format.hourOfDayFormat.posStart));

        if (format.minuteOfHourFormat != null)
            traceHeader.setMinuteOfHour(byteAToShort(buffer, format.minuteOfHourFormat.posStart));

        if (format.secondOfMinuteFormat != null)
            traceHeader.setSecondOfMinute(byteAToShort(buffer, format.secondOfMinuteFormat.posStart));

        if (format.timeBasisCodeFormat != null)
            traceHeader.setTimeBasisCode(byteAToShort(buffer, format.timeBasisCodeFormat.posStart));

        if (format.traceWeightingFactorFormat != null)
            traceHeader.setTraceWeightingFactor(byteAToShort(buffer, format.traceWeightingFactorFormat.posStart));

        if (format.geophoneGroupNumberOfRollSwitchPositionOneFormat != null)
            traceHeader.setGeophoneGroupNumberOfRollSwitchPositionOne(byteAToShort(buffer, format.geophoneGroupNumberOfRollSwitchPositionOneFormat.posStart));

        if (format.geophoneGroupNumberOfTraceNumberOneWOFRFormat != null)
            traceHeader.setGeophoneGroupNumberOfTraceNumberOneWOFR(byteAToShort(buffer, format.geophoneGroupNumberOfTraceNumberOneWOFRFormat.posStart));

        if (format.geophoneGroupNumberOfLastTraceWOFRFormat != null)
            traceHeader.setGeophoneGroupNumberOfLastTraceWOFR(byteAToShort(buffer, format.geophoneGroupNumberOfLastTraceWOFRFormat.posStart));

        if (format.gapSizeFormat != null)
            traceHeader.setGapSize(byteAToShort(buffer, format.gapSizeFormat.posStart));

        if (format.overTravelFormat != null)
            traceHeader.setOverTravel(byteAToShort(buffer, format.overTravelFormat.posStart));

        if (format.xOfCDPPositionFormat != null)
            traceHeader.setxOfCDPPosition(byteAToInt(buffer, format.xOfCDPPositionFormat.posStart));

        if (format.yOfCDPPositionFormat != null)
            traceHeader.setyOfCDPPosition(byteAToInt(buffer, format.yOfCDPPositionFormat.posStart));

        if (format.inLineNumberFormat != null)
            traceHeader.setInLineNumber(byteAToInt(buffer, format.inLineNumberFormat.posStart));

        if (format.crossLineNumberFormat != null)
            traceHeader.setCrossLineNumber(byteAToInt(buffer, format.crossLineNumberFormat.posStart));

        if (format.shotpointNumberFormat != null)
            traceHeader.setShotpointNumber(byteAToInt(buffer, format.shotpointNumberFormat.posStart));

        if (format.scalarForSPNumberFormat != null)
            traceHeader.setScalarForSPNumber(byteAToShort(buffer, format.scalarForSPNumberFormat.posStart));

        if (format.traceValuesMUFormat != null)
            traceHeader.setTraceValuesMU(byteAToShort(buffer, format.traceValuesMUFormat.posStart));

        if (format.transductionConstantFormat != null) {
            final byte[] transductionConstant = Arrays.copyOfRange(buffer, format.transductionConstantFormat.posStart, format.transductionConstantFormat.posEnd);
            traceHeader.setTransductionConstant(transductionConstant);
        }

        if (format.transductionUnitsFormat != null)
            traceHeader.setTransductionUnits(byteAToShort(buffer, format.transductionUnitsFormat.posStart));

        if (format.deviceTraceIdentifierFormat != null)
            traceHeader.setDeviceTraceIdentifier(byteAToShort(buffer, format.deviceTraceIdentifierFormat.posStart));

        if (format.scalarToBeAppliedToTimesFormat != null)
            traceHeader.setScalarForTimes(byteAToShort(buffer, format.scalarToBeAppliedToTimesFormat.posStart));

        if (format.sourceTypeOrientationFormat != null)
            traceHeader.setSourceTypeOrientation(byteAToShort(buffer, format.sourceTypeOrientationFormat.posStart));

        if (format.sourceEnergyDirectionFormat != null) {
            final byte[] sourceEnergyDirection = Arrays.copyOfRange(buffer, format.sourceEnergyDirectionFormat.posStart, format.sourceEnergyDirectionFormat.posEnd);
            traceHeader.setSourceEnergyDirection(sourceEnergyDirection);
        }

        if (format.sourceMeasurementFormat != null) {
            final byte[] sourceMeasurementFormat = Arrays.copyOfRange(buffer, format.sourceMeasurementFormat.posStart, format.sourceMeasurementFormat.posEnd);
            traceHeader.setSourceMeasurement(sourceMeasurementFormat);
        }

        if (format.sourceMeasurementUnitFormat != null)
            traceHeader.setSourceMeasurementUnit(byteAToShort(buffer, format.sourceMeasurementUnitFormat.posStart));

        return traceHeader;
    }
}
