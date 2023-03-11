import * as React from 'react';

import { ExpoSampleGyroViewProps } from './ExpoSampleGyroView.types';

export default function ExpoSampleGyroView(props: ExpoSampleGyroViewProps) {
  return (
    <div>
      <span>{props.name}</span>
    </div>
  );
}
