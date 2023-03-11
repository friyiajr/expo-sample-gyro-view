import { requireNativeViewManager } from 'expo-modules-core';
import * as React from 'react';

import { ExpoSampleGyroViewProps } from './ExpoSampleGyroView.types';

const NativeView: React.ComponentType<ExpoSampleGyroViewProps> =
  requireNativeViewManager('ExpoSampleGyroView');

export default function ExpoSampleGyroView(props: ExpoSampleGyroViewProps) {
  return <NativeView {...props} />;
}
