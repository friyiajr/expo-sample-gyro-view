import { GyroView } from "expo-sample-gyro-view";
import { useRef, useState } from "react";
import { TouchableOpacity, SafeAreaView, Text, Alert } from "react-native";

export default function App() {
  const [isTracking, setIsTracking] = useState(false);
  const isModalVisible = useRef(false);

  return (
    <SafeAreaView style={{ flex: 1 }}>
      <GyroView
        track={isTracking}
        placeholderText="START"
        style={{ flex: 1 }}
        onGyroEvent={({ nativeEvent: { y } }) => {
          if (y > 0 && !isModalVisible.current) {
            isModalVisible.current = true;
            Alert.alert("Oops!", "You are upside down 😱", [
              {
                text: "OK",
                style: "default",
                onPress: () => {
                  isModalVisible.current = false;
                },
              },
            ]);
          }
        }}
      />
      <TouchableOpacity
        style={{
          height: 60,
          justifyContent: "center",
          alignItems: "center",
          backgroundColor: "purple",
          marginHorizontal: 20,
          borderRadius: 8,
        }}
        onPress={() => {
          setIsTracking(!isTracking);
        }}
      >
        <Text style={{ color: "white", fontSize: 22, fontWeight: "bold" }}>
          Track
        </Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
}
