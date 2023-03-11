import { NativeModulesProxy, EventEmitter, Subscription } from 'expo-modules-core';

// Import the native module. On web, it will be resolved to ExpoSampleGyroView.web.ts
// and on native platforms to ExpoSampleGyroView.ts
import ExpoSampleGyroViewModule from './ExpoSampleGyroViewModule';
import ExpoSampleGyroView from './ExpoSampleGyroView';
import { ChangeEventPayload, ExpoSampleGyroViewProps } from './ExpoSampleGyroView.types';

// Get the native constant value.
export const PI = ExpoSampleGyroViewModule.PI;

export function hello(): string {
  return ExpoSampleGyroViewModule.hello();
}

export async function setValueAsync(value: string) {
  return await ExpoSampleGyroViewModule.setValueAsync(value);
}

const emitter = new EventEmitter(ExpoSampleGyroViewModule ?? NativeModulesProxy.ExpoSampleGyroView);

export function addChangeListener(listener: (event: ChangeEventPayload) => void): Subscription {
  return emitter.addListener<ChangeEventPayload>('onChange', listener);
}

export { ExpoSampleGyroView, ExpoSampleGyroViewProps, ChangeEventPayload };
