package sigrun.serialization;

import sigrun.common.*;

import static sigrun.converters.ByteANumberConverter.*;

@SuppressWarnings({"WeakerAccess", "ConstantConditions"})
public class BinaryHeaderReader {
    public final BinaryHeaderFormat format;

    public BinaryHeaderReader(BinaryHeaderFormat format) {
        this.format = format;
    }

    public BinaryHeader read(final byte[] buffer) {
        assert buffer.length == BinaryHeader.BIN_HEADER_LENGTH;
        final BinaryHeader binaryHeader = new BinaryHeader();

        if (format.jobIdFormat != null)
            binaryHeader.setJobId(byteAToInt(buffer, format.jobIdFormat.posStart));

        if (format.lineNumberFormat != null)
            binaryHeader.setLineNumber(byteAToInt(buffer, format.lineNumberFormat.posStart));

        if (format.reelNumberFormat != null)
            binaryHeader.setReelNumber(byteAToInt(buffer, format.reelNumberFormat.posStart));

        if (format.dataTracesPerEnsembleFormat != null)
            binaryHeader.setDataTracesPerEnsemble(byteAToShort(buffer, format.dataTracesPerEnsembleFormat.posStart));

        if (format.auxiliaryTracesPerEnsembleFormat != null)
            binaryHeader.setAuxiliaryTracesPerEnsemble(byteAToShort(buffer, format.auxiliaryTracesPerEnsembleFormat.posStart));

        if (format.sampleIntervalFormat != null)
            binaryHeader.setSampleInterval(byteAToShort(buffer, format.sampleIntervalFormat.posStart));

        if (format.sampleIntervalOfOFRFormat != null)
            binaryHeader.setSampleIntervalOfOFR(byteAToShort(buffer, format.sampleIntervalOfOFRFormat.posStart));

        if (format.samplesPerDataTraceFormat != null)
            binaryHeader.setSamplesPerDataTrace(byteAToShort(buffer, format.samplesPerDataTraceFormat.posStart));

        if (format.samplesPerDataTraceOfOFRFormat != null)
            binaryHeader.setSamplesPerDataTraceOfOFR(byteAToShort(buffer, format.samplesPerDataTraceOfOFRFormat.posStart));

        if (format.dataSampleCodeFormat != null)
            binaryHeader.setDataSampleCode(DataSample.create(byteAToShort(buffer, format.dataSampleCodeFormat.posStart)));

        if (format.ensembleFoldFormat != null)
            binaryHeader.setEnsembleFold(byteAToShort(buffer, format.ensembleFoldFormat.posStart));

        if (format.traceSortingFormat != null)
            binaryHeader.setTraceSorting(TraceSorting.create(byteAToShort(buffer, format.traceSortingFormat.posStart)));

        if (format.verticalSumCodeFormat != null)
            binaryHeader.setVerticalSumCode(byteAToShort(buffer, format.verticalSumCodeFormat.posStart));

        if (format.sweepFrequencyAtStartFormat != null)
            binaryHeader.setSweepFrequencyAtStart(byteAToShort(buffer, format.sweepFrequencyAtStartFormat.posStart));

        if (format.sweepFrequencyAtEndFormat != null)
            binaryHeader.setSweepFrequencyAtEnd(byteAToShort(buffer, format.sweepFrequencyAtEndFormat.posStart));

        if (format.sweepLengthFormat != null)
            binaryHeader.setSweepLength(byteAToShort(buffer, format.sweepLengthFormat.posStart));

        if (format.sweepTypeCodeFormat != null)
            binaryHeader.setSweepTypeCode(SweepTypeCode.create(byteAToShort(buffer, format.sweepTypeCodeFormat.posStart)));

        if (format.traceNumberFormat != null)
            binaryHeader.setTraceNumber(byteAToShort(buffer, format.traceNumberFormat.posStart));

        if (format.taperLengthAtStartFormat != null)
            binaryHeader.setTaperLengthAtStart(byteAToShort(buffer, format.taperLengthAtStartFormat.posStart));

        if (format.taperLengthAtEndFormat != null)
            binaryHeader.setTaperLengthAtEnd(byteAToShort(buffer, format.taperLengthAtEndFormat.posStart));

        if (format.taperTypeFormat != null)
            binaryHeader.setTaperType(TaperType.create(byteAToShort(buffer, format.taperTypeFormat.posStart)));

        if (format.dataTracesCorrelatedFormat != null)
            binaryHeader.setDataTracesCorrelated(byteAToShort(buffer, format.dataTracesCorrelatedFormat.posStart));

        if (format.binaryGainRecoveredFormat != null)
            binaryHeader.setBinaryGainRecovered(byteAToShort(buffer, format.binaryGainRecoveredFormat.posStart));

        if (format.amplitudeRecoveryMethodFormat != null)
            binaryHeader.setAmplitudeRecoveryMethod(byteAToShort(buffer, format.amplitudeRecoveryMethodFormat.posStart));

        if (format.measurementSystemFormat != null)
            binaryHeader.setMeasurementSystem(byteAToShort(buffer, format.measurementSystemFormat.posStart));

        if (format.impulseSignalPolarityFormat != null)
            binaryHeader.setImpulseSignalPolarity(byteAToShort(buffer, format.impulseSignalPolarityFormat.posStart));

        if (format.vibratoryPolarityCodeFormat != null)
            binaryHeader.setVibratoryPolarityCode(byteAToShort(buffer, format.vibratoryPolarityCodeFormat.posStart));

        if (format.segyFormatRevNumberFormat != null)
            binaryHeader.setSegyFormatRevNumber(byteAToShort(buffer, format.segyFormatRevNumberFormat.posStart));

        if (format.fixedLengthTraceFlagFormat != null)
            binaryHeader.setFixedLengthTraceFlag(byteAToShort(buffer, format.fixedLengthTraceFlagFormat.posStart));

        if (format.numberOf3200ByteFormat != null)
            binaryHeader.setNumberOf3200Byte(byteAToShort(buffer, format.numberOf3200ByteFormat.posStart));

        return binaryHeader;
    }
}
