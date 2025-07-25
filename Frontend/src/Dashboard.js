// SecureSightDashboard.jsx

import React from 'react';
import { Box, Heading, SimpleGrid, Image, Text, VStack } from '@chakra-ui/react';

const feeds = [
    {
        id: 1,
        name: 'CCTV 1',
        imageUrl: 'https://via.placeholder.com/400x250?text=CCTV+1',
        activity: 'No Threat Detected',
    },
    {
        id: 2,
        name: 'CCTV 2',
        imageUrl: 'https://via.placeholder.com/400x250?text=CCTV+2',
        activity: 'Gun Detected ⚠️',
    },
    {
        id: 3,
        name: 'CCTV 3',
        imageUrl: 'https://via.placeholder.com/400x250?text=CCTV+3',
        activity: 'Unauthorized Entry ❌',
    },
];

const SecureSightDashboard = () => {
    return (
        <Box p={8}>
            <Heading mb={6} textAlign="center">
                SecureSight Dashboard
            </Heading>
            <SimpleGrid columns={{ base: 1, md: 3 }} spacing={6}>
                {feeds.map((feed) => (
                    <VStack
                        key={feed.id}
                        p={4}
                        borderWidth={1}
                        borderRadius="xl"
                        boxShadow="md"
                        spacing={3}
                    >
                        <Image
                            src={feed.imageUrl}
                            alt={feed.name}
                            borderRadius="lg"
                            objectFit="cover"
                            width="100%"
                            height="auto"
                        />
                        <Text fontWeight="bold">{feed.name}</Text>
                        <Text color={feed.activity.includes('Detected') || feed.activity.includes('Unauthorized') ? 'red.500' : 'green.500'}>
                            {feed.activity}
                        </Text>
                    </VStack>
                ))}
            </SimpleGrid>
        </Box>
    );
};

export default SecureSightDashboard;
