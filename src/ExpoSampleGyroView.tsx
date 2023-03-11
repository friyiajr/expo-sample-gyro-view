import { requireNativeViewManager } from "expo-modules-core";
import * as React from "react";
import { ViewProps } from "react-native";

export type OnGyroEvent = {
  y: number;
};

export type Props = {
  placeholderText?: string;
  track: boolean;
  onGyroEvent?: (event: { nativeEvent: OnGyroEvent }) => void;
} & ViewProps;

const NativeView: React.ComponentType<Props> =
  requireNativeViewManager("ExpoSampleGyroView");

export default function ExpoGyroView(props: Props) {
  return <NativeView {...props} />;
}
